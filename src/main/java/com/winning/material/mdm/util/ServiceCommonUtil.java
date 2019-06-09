package com.winning.material.mdm.util;

import com.winning.base.akso.base.WinBaseQueryRequest;
import com.winning.material.mdm.domain.request.BasePageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *  @author liu_chao
 *  @date 2019/5/5
 */
public class ServiceCommonUtil {

    protected ServiceCommonUtil() {throw new IllegalStateException("Utility class");}

    public static int getCorrectPageSize(int pageSize) {
        if (pageSize < 1) {
            pageSize = 100;
        }
        return pageSize;
    }

	public static Pageable convert(WinBaseQueryRequest request) {
        request.setPageSize(getCorrectPageSize(request.getPageSize()));
        return PageRequest.of(CommonNativeSqlUtil.getCorrectPageNo(request.getPageNo()), request.getPageSize());
	}

	public static Pageable convert(BasePageRequest request) {
        request.setPageSize(getCorrectPageSize(request.getPageSize()));
        return PageRequest.of(CommonNativeSqlUtil.getCorrectPageNo(request.getPageNo()), request.getPageSize());
	}

    public static <T> Specification<T> buildSpec(Map<String,Object> equalMap, Map<String,List<Object>> inMap, Map<String,Object> likeMap){
        return new Specification<T>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates=new ArrayList<>();
				if(equalMap!=null) {
					for (Entry<String, Object> entry : equalMap.entrySet()) {
						Predicate predicateIsDel=criteriaBuilder.equal(root.get(entry.getKey()), entry.getValue());
						predicates.add(predicateIsDel);
					}
				}
				if(inMap!=null) {
					for (Entry<String, List<Object>> entry : inMap.entrySet()) {
						Predicate predicateIsDel=root.get(entry.getKey()).in(entry.getValue());
						predicates.add(predicateIsDel);
					}
				}
				if(likeMap!=null) {
					for (Entry<String,Object> entry : likeMap.entrySet()) {
						Predicate predicateIsDel=criteriaBuilder.like(root.get(entry.getKey()), entry.getValue().toString());
						predicates.add(predicateIsDel);
					}
				}
				return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
			}
		};
	}

    /**
     * @desc 在buildSpec方法的基础上修改like条件的连接方式为or
     * @auth f_zl
     * @param equalMap
     * @param inMap
     * @param likeMap
     * @param <T>
     * @return
     */
	public static <T> Specification<T> buildSpecLikeOr(Map<String,Object> equalMap, Map<String,List<Object>> inMap, Map<String,Object> likeMap){
        return new Specification<T>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if(equalMap!=null) {
                    for (Entry<String, Object> entry : equalMap.entrySet()) {
                        Predicate predicateIsDel=criteriaBuilder.equal(root.get(entry.getKey()), entry.getValue());
                        predicates.add(predicateIsDel);
                    }
                }
                if(inMap!=null) {
                    for (Entry<String, List<Object>> entry : inMap.entrySet()) {
                        Predicate predicateIsDel=root.get(entry.getKey()).in(entry.getValue());
                        predicates.add(predicateIsDel);
                    }
                }

                //like条件的连接方式为or
                if(likeMap!=null) {
                    List<Predicate> tempList=new ArrayList<>();
                    for (Entry<String,Object> entry : likeMap.entrySet()) {
                        Predicate predicateIsDel=criteriaBuilder.like(root.get(entry.getKey()).as(String.class),
                            "%" + entry.getValue().toString() + "%");
                        tempList.add(predicateIsDel);
                    }
                    Predicate[] predicateTempArray = new Predicate[likeMap.size()];
                    predicates.add(criteriaBuilder.or(tempList.toArray(predicateTempArray)));
                }
                Predicate[] predicatesArray = new Predicate[predicates.size()];
                return query.where(predicates.toArray(predicatesArray)).getRestriction();
            }
        };
    }

}
